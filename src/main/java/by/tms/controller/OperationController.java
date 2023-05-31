package by.tms.controller;

import by.tms.entity.OperationType;
import by.tms.entity.User;
import by.tms.factory.OperationFactory;
import by.tms.service.CalculatorOperation;
import by.tms.service.OperationService;
import by.tms.validator.CalculatorValidator;
import jdk.dynalink.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;


    @GetMapping
    public String calc() {
        return "calc";
    }

    @PostMapping("/calc")
    public String calc(@Valid Operation operation, BindingResult bindingResult,
                       HttpSession session,
                       Model model) {

        OperationType opType = OperationType.valueOf(operationType.toUpperCase());

        User user = (User) session.getAttribute("user");

        double result = operationService.calculate(operation);

        model.addAttribute("result", result);
        return "calc";
    }


    @GetMapping("/history")
    public String history(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CalculatorOperation> operationList = operationService.findAllByUsername(user.getUsername());
        model.addAttribute("operationList", operationList);
        return "history";
    }
}

