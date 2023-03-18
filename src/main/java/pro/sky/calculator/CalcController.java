package pro.sky.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")

public class CalcController {
    public final CalcService service;

    public CalcController(CalcService service) {
        this.service = service;
    }

@GetMapping
    public String hello() {
        return "Добро пожаловать в калькулятор";
    }
@GetMapping("/plus")
    public String plus(@RequestParam (value = "num1", required = false) Integer a,
                       @RequestParam (value = "num2", required = false) Integer b)
        { return buildView(a, b, "+"); }

    @GetMapping("/minus")
    public String minus(@RequestParam (value = "num1", required = false) Integer a,
                        @RequestParam (value = "num2", required = false) Integer b)
        { return buildView(a, b, "-"); }

    @GetMapping("/multiply")
    public String multiply(@RequestParam (value = "num1", required = false) Integer a,
                           @RequestParam (value = "num2", required = false) Integer b)
    { return buildView(a, b, "*"); }

    @GetMapping("/divide")
    public String divide(@RequestParam (value = "num1", required = false) Integer a,
                         @RequestParam (value = "num2", required = false) Integer b)
    { return buildView(a, b, "/"); }

    public String buildView(Integer a, Integer b, String operator) {
        Number result = 0;
    if (a==null || b==null)
    { return "Не передан параметр"; }
    switch (operator) {
        case "+":
            result = service.plus(a, b);
            break;
        case "-":
            result = service.minus(a, b);
            break;
        case "*":
            result = service.multiply(a, b);
            break;
        case "/":
            if (b == 0)
            { return "Нельзя делить на ноль"; }
            result = service.divide(a, b);
            break; }
    return a + " " + operator + " " + b + " = " + result;
    }
}
