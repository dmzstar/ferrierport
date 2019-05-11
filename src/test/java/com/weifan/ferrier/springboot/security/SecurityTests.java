package com.weifan.ferrier.springboot.security;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import javax.validation.ConstraintViolationException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.weifan.ferrier.springboot.security.service.RoleService;
import com.weifan.ferrier.springboot.security.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class SecurityTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void testAll() throws Exception {
		
		/**
		exceptThrow().assertMsg(CoreMatchers::is,"username.error.blank")
		.fetchMsg((t) -> "username.error.blank")
		.exec(() -> userService.create("", "123456zdm"));
		*/
		
		exceptThrow(ConstraintViolationException.class).assertMsg(CoreMatchers::is,"username.error.blank")
		.fetchMsg((t) -> {
			var c = (ConstraintViolationException)t;
			return c.getConstraintViolations().iterator().next().getMessage();
		})
		.exec(() -> userService.create("", "123456zdm"));

		// 不能重复创建角色
		try {
			roleService.create("ROLE_ADMIN", "ROLE_ADMIN");
		} catch (Throwable t) {
			assertThat(t.getMessage(), is("role.exists"));
		}

		assertTrue(roleService.isExists("ROLE_ADMIN"));

		roleService.create("ROLE_MEMBER");
		
		assertTrue(roleService.isExists("ROLE_MEMBER"));

		// 不能重复创建用户
		try {
			userService.create("admin", "123456zdm", "ROLE_ADMIN");
		} catch (Throwable t) {
			assertThat(t.getMessage(), is("user.error.exists"));
		}
		
		//不能添加不存在的角色
		try {
			userService.create("ccc", "123456zdm", "ROLE_SSS");
		} catch (Throwable t) {
			assertThat(t.getMessage(), is("role.error.notfound"));
		}
		
		//不能添加不存在的角色
		try {
			userService.addRole("admin", "ROLE_SSS");
		} catch (Throwable t) {
			assertThat(t.getMessage(), is("role.error.notfound"));
		}
		
		//不能新增不存在的角色
		throwIs("user.error.notfound",() -> userService.addRole("noneexistUser", "ROLE_ADMIN"));
		
		throwIs(RuntimeException.class,"user.error.notfound",() -> userService.addRole("noneexistUser", "ROLE_ADMIN"));
		
		
		thrown(RuntimeException.class,"user.error.notfound",CoreMatchers::is
				,() -> userService.addRole("noneexistUser", "ROLE_ADMIN"));
		
		
		//mvc.perform(get("/admin")).andExpect(authenticated());

		// mvc.perform(get("/admin")).andExpect(unauthenticated());

	}
	
	public static void throwIs(String msg,ExceptionFun fun) {
		try {
			fun.test();
		}catch(Throwable t) {
			assertThat(t.getMessage(), is(msg));
		}
	}
	
	public static ExceptThrowBuilder exceptThrow() {
		return new ExceptThrowBuilder();
	}
	
	public static ExceptThrowBuilder exceptThrow(Class<? extends Throwable> exceptionClass) {
		return new ExceptThrowBuilder(exceptionClass);
	}
	
	public static void exceptThrow(ExceptionFun fun) {
		try {
			fun.test();
		}catch(Throwable t) {
			//assertThat(t.getMessage(),msgAssert.test(msg));
		}
	}
	
	public static class ExceptThrowBuilder{
		
		private Class<? extends Throwable> exceptedThrown;
		
		public ExceptThrowBuilder() {}
		public ExceptThrowBuilder(Class<? extends Throwable> t) {
			this.exceptedThrown = t;
		}
		
		public MsgStep assertMsg(ExceptionFun2<String> fun,String msg) {
			return new MsgStep(fun,msg);
		}
		
		class MsgStep{
			private String tested;
			private ExceptionFun2<String> msgFun;
			private FetchMsgFun<Throwable> fetchMsgFun;
			public  MsgStep(ExceptionFun2<String> fun,String tested) {
				this.msgFun = fun;
				this.tested = tested;
			}
			
			public MsgStep fetchMsg(FetchMsgFun<Throwable> fetchMsgFun) {
				this.fetchMsgFun = fetchMsgFun;
				return this;
			}

			public void exec(ExceptionFun fun) {
				try {
					fun.test();
				}catch(Throwable t) {
					
					var cause = findCause(exceptedThrown, t);
					
					if(exceptedThrown != null) {
						
						System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$" + cause);
						
						if(cause == null) {
							throw new RuntimeException("not excepted exception");
						}
						
					}
					
					if(fetchMsgFun != null) {
						assertThat(fetchMsgFun.test(cause),msgFun.test(tested));
					}else {
						assertThat(t.getMessage(),msgFun.test(tested));
					}
				}
			}
		}
		
	}
	
	public static <T extends Throwable> T findCause(Class<? extends Throwable> excepted,Throwable t) {
		if(t == null) return null;
		if(t.getClass() == excepted) {
			return (T)t;
		}else {
			t = t.getCause();
			return findCause(excepted, t);
		}
	}
	
	public static void exceptThrow(String msg,ExceptionFun2<String> msgAssert,ExceptionFun fun) {
		try {
			fun.test();
		}catch(Throwable t) {
			assertThat(t.getMessage(),msgAssert.test(msg));
		}
	}
	
	public static void thrown(Class<? extends Throwable> exceptionClass,String msg,ExceptionFun2<String> msgAssert,ExceptionFun fun) {
		try {
			fun.test();
		}catch(Throwable t) {
			if(t.getClass() == exceptionClass) {
				assertThat(t.getMessage(),msgAssert.test(msg));
			}else {
				throw new RuntimeException("not excepted Exception");
			}
		}
	}
	
	public static void throwIs(Class<? extends Throwable> exceptionClass,String msg,ExceptionFun fun) {
		try {
			fun.test();
		}catch(Throwable t) {
			if(t.getClass() == exceptionClass) {
				assertThat(t.getMessage(), is(msg));
			}else {
				throw new RuntimeException("not excepted Exception");
			}
		}
	}
	
	@FunctionalInterface
	public static interface ExceptionFun{
		public void test();
	}
	
	@FunctionalInterface
	public static interface FetchMsgFun<T>{
		public String test(T t);
	}
	
	public static interface ExceptionFun2<T>{
		public Matcher<T> test(T t);
	}

}
