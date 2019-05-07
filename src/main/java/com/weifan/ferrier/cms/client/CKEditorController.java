package com.weifan.ferrier.cms.client;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weifan.ferrier.cms.domain.entities.Article;
import com.weifan.ferrier.cms.domain.repositories.ArticleRepository;
import com.weifan.ferrier.springboot.mvc.COCViewController;

@Controller
@RequestMapping("/cms/client")
public class CKEditorController implements COCViewController{
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/editor")
	public String editor(ModelMap map) {
		
		if(articleRepository.count() == 0) {
			for(int i=0;i<2;i++) {
				var a = new Article();
				a.setDetails("details" + i);
				articleRepository.save(a);
			}
		}
		
		var article = new Article();
		map.put("article", article);
		return cocView();
	}
	
	@GetMapping("/editor/article/{id}")
	@ResponseBody
	public Object get(@RequestParam @NotBlank Long id) {
		
		var article = articleRepository.findById(id).get();
		return article;
		
	}
	
	@PostMapping("/editor/save")
	public String save(@RequestParam String editor1,RedirectAttributes attrs) {
		
		var article = new Article();
		article.setDetails(editor1);
		articleRepository.save(article);
		attrs.addFlashAttribute("message","成功");
		return "redirect:/cms/client/editor";
		
	}
	
}
