package jp.gihyo.wdpress.qiita.web

import jp.gihyo.wdpress.qiita.service.QiitaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class QiitaWebController {
    @Autowired
    lateinit var qiitaService: QiitaService

    @GetMapping("/items")
    fun showAllItemsPage(): ModelAndView {
        val modelAndView = ModelAndView("items")
        val items = qiitaService.findAll()
        modelAndView.addObject("items", items)
        return modelAndView
    }
}