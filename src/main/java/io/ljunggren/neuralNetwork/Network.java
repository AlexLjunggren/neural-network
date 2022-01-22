package io.ljunggren.neuralNetwork;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Network {

    private double[][] inputToHiddenWeights;
    private double[][] hiddenToOutputWeights;
    private double[][] hiddenBias;
    private double[][] outputBias;
    private String activationClass;
    private double learnRate;
    private List<String> labels;

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
    
}
