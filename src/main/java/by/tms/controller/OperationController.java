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
    private CalculatorValidator calculatorValidator;
    private OperationFactory operationFactory;

    @GetMapping
    public String calc() {
        return "calc";
    }

    @PostMapping("/calc")
    public String calc(@Valid Operation operation, BindingResult bindingResult,
                       @RequestParam("num1") String num1,
                       @RequestParam("num2") String num2,
                       @RequestParam("type") String operationType,
                       HttpSession session,
                       Model model) {
        if (!calculatorValidator.isValidDigits(num1)) {
            model.addAttribute("error","Invalid num1!");
            return "forward:/pages/calc.jsp";
        }

        double sNum1 = Double.parseDouble(num1);

        if (!calculatorValidator.isValidDigits(num2)) {
            model.addAttribute("error","Invalid num2!");
            return "forward:/pages/calc.jsp";
        }

        double sNum2 = Double.parseDouble(num2);

        OperationType opType = OperationType.valueOf(operationType.toUpperCase());

        User user = (User) session.getAttribute("user");

        CalculatorOperation instance = operationFactory.getInstance(sNum1, sNum2, opType, user);

        double result = operationService.calculate(instance);

        model.addAttribute("result", result);
        return "forward:/pages/calc.jsp";
    }


    @GetMapping("/history")
    public String history(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CalculatorOperation> operationList = operationService.findAllByUsername(user.getUsername());
        model.addAttribute("operationList", operationList);
        return "forward:/pages/history.jsp";
    }
}

