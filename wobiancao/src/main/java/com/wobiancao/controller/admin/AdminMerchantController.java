package com.wobiancao.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wobiancao.entity.Merchant;
import com.wobiancao.protocol.ajax.AjaxFailResponse;
import com.wobiancao.protocol.ajax.AjaxSuccessResponse;
import com.wobiancao.repository.MerchantRepository;
import com.wobiancao.utils.FileUtils;
import com.wobiancao.utils.IdUtils;

@Controller
@RequestMapping(value = "/admin/merchant")
public class AdminMerchantController {

	private static final String CURRENT_FUNCTION = "merchant";

	@Autowired
	private MerchantRepository merchantRepository;

	@RequestMapping(value = "/list")
	public String list(Pageable pageable, Model model, HttpSession session) {
		Object admin = session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin/login";
		}
		
		Page<Merchant> page = merchantRepository.findAll(pageable);
		model.addAttribute("page", page);
		return String.format("admin/%s/%s_list", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Merchant merchant = merchantRepository.findOne(id);
			model.addAttribute("item", merchant);
		}
		return String.format("admin/%s/%s_edit", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestParam(required = false) Long id, @ModelAttribute Merchant input) {
		if (id != null) {
			Merchant merchant = merchantRepository.findOne(id);
			if (merchant == null) {
				return new AjaxFailResponse("商户不存在").toString();
			}

			// String fileExtension =
			// FileUtils.getFileExtension(logoFile.getOriginalFilename());
			// String filename = "merchant_" + id + "." + fileExtension;
			// boolean saveSuccess = saveLogoFile(filename, logoFile);
			// if (!saveSuccess) {
			// return new AjaxFailResponse("图片保存出错").toString();
			// }

			merchant.setName(input.getName());
			merchant.setDescription(input.getDescription());
			merchant.setLogo(input.getLogo());
			merchantRepository.save(merchant);
		} else {
			merchantRepository.save(input);
		}

		return new AjaxSuccessResponse().toString();
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(HttpServletRequest request, @RequestParam("imageFile") MultipartFile imageFile) {
		String filedir = "/img/shop_pic/";
		String uploadLocation = request.getRealPath(filedir);
		String id = IdUtils.generateRandomId(16);
		String fileExtension = FileUtils.getFileExtension(imageFile.getOriginalFilename());
		String filename = id + "." + fileExtension;
		boolean saveSuccess = saveImageFile(uploadLocation, filename, imageFile);
		if (!saveSuccess) {
			return new AjaxFailResponse("图片保存出错").toString();
		}
		return "{\"picUrl\": \"" + filedir + filename + "\"}";
	}

	private boolean saveImageFile(String uploadLocation, String filename, MultipartFile logoFile) {
		File uploadDir = new File(uploadLocation);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		String path = uploadLocation + filename;
		try {
			logoFile.transferTo(new File(path));
			return true;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(@RequestParam Long id, Model model) {
		Merchant merchant = merchantRepository.findOne(id);
		model.addAttribute("item", merchant);
		return String.format("admin/%s/%s_confirm", CURRENT_FUNCTION, CURRENT_FUNCTION);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam Long id) {
		Merchant merchant = merchantRepository.findOne(id);
		if (merchant == null) {
			return new AjaxFailResponse("商户不存在").toString();
		}
		merchantRepository.delete(merchant);
		return new AjaxSuccessResponse().toString();
	}

}
