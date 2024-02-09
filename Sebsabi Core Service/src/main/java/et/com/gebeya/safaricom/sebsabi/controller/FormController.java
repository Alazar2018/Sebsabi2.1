package et.com.gebeya.safaricom.sebsabi.controller;

import et.com.gebeya.safaricom.sebsabi.dto.FormDto;
import et.com.gebeya.safaricom.sebsabi.dto.FormQuestionDto;
import et.com.gebeya.safaricom.sebsabi.dto.UserResponseDto;
import et.com.gebeya.safaricom.sebsabi.model.Form;
import et.com.gebeya.safaricom.sebsabi.model.FormQuestion;
import et.com.gebeya.safaricom.sebsabi.model.UserResponse;
import et.com.gebeya.safaricom.sebsabi.service.FormQuestionService;
import et.com.gebeya.safaricom.sebsabi.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/forms")
public class FormController {

    private final FormService formService;
    private final FormQuestionService formQuestionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createForm(@RequestBody Form formDTO ,@RequestHeader("loggedInUser") String username)  {
        System.out.println("Logged in USer Details :- "+username);
        formService.createForm(formDTO);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Form getFormById(@PathVariable Long id) {
        Form form = formService.getFormById(id);
        return form;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Form updateForm(@PathVariable Long id, @RequestBody FormDto formDTO) throws InvocationTargetException, IllegalAccessException {
        Form updatedForm = formService.updateForm(id, formDTO);
        return  updatedForm;
    }

    @PostMapping("/add/question-to-form")
    public Form addQuestionToForm(@RequestParam Long formID, @RequestBody FormQuestionDto questionDTO) throws InvocationTargetException, IllegalAccessException {
        return formService.addQuestionToForm(formID, questionDTO);
    }
    @GetMapping("/view/questionOfForm")
    public List<FormQuestion> viewQuestions(@RequestParam Long formID) throws InvocationTargetException, IllegalAccessException {
        return formQuestionService.getFormQuestionBYFOrmID(formID);
    }
    @PostMapping("/submit-response")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse submitResponse(@RequestBody UserResponseDto responseDTO) {
        return formService.submitResponse(responseDTO);
    }
}