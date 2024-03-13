package org.example.springadvanced.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
    private double page;
    private List<MovieResultDTO> results;
    private double total_pages;
    private double total_results;
}
