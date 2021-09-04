package liv2train.controller;

import liv2train.entity.TrainingCenter;
import liv2train.service.TrainingCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/trainingCenters")
public class TrainingCenterController {

    private final TrainingCenterService trainingCenterService;

    @Autowired
    public TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingCenter>> getAllCenters(
            @RequestParam(required = false) String centerCode,
            @RequestParam(required = false) String centerName,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String pinCode,
            @RequestParam(required = false) String course
    ) {
        return new ResponseEntity<>(
                trainingCenterService.getTrainingCenters(
                        centerCode,
                        centerName,
                        city,
                        state,
                        pinCode,
                        course
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<TrainingCenter> addTrainingCenter(
            @Valid @RequestBody TrainingCenter trainingCenter
    ) {
        return new ResponseEntity<>(
                trainingCenterService.createTrainingCenter(trainingCenter),
                HttpStatus.OK
        );
    }

}
