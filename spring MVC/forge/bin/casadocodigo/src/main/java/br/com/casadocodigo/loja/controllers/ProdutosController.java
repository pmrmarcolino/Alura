package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired // injetar o dao
    private ProdutoDao pdao;

    @Autowired
    private FileSaver fileSaver;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ProdutoValidation());
    }

    @RequestMapping("form")
    public ModelAndView form(Produto produto) {
        ModelAndView modelandView = new ModelAndView("produtos/form");
        modelandView.addObject("tipos", TipoPreco.values());
        return modelandView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result,
            RedirectAttributes ra) {

        new FileSaver();

        if (result.hasErrors()) {
            return form(produto);
        }

        String path = fileSaver.write("arquivos-sumario", sumario);
        produto.setSumarioPath(path);

        pdao.grava(produto);
        ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
        return new ModelAndView("redirect:produtos");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listar() {
        List<Produto> produtos = pdao.listar();
        ModelAndView modelAndView = new ModelAndView("produtos/lista");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    @RequestMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
        Produto produto = pdao.find(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }
}
