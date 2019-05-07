package com.weifan.ferrier.showcase.fileupload.servlet;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weifan.ferrier.springboot.mvc.COCViewController;

import lombok.extern.slf4j.Slf4j;

public class Controllers {
	
	@Slf4j
	@Controller
	@RequestMapping("/showcase/upload")
	public static class ServletFileupload implements COCViewController{

		@GetMapping("/servlet")
		public String index(ModelMap model) {
			model.put("fileObject", new HashMap<>());
			return cocView();
		}

		@PostMapping("/servlet")
		public String handleFileUpload(@RequestParam("file") MultipartFile file,
				RedirectAttributes redirectAttributes) {
			log.info(file.getName());
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded " + file.getOriginalFilename() + "!");
			return "redirect:" + cocView();
			
		}

	}

}
