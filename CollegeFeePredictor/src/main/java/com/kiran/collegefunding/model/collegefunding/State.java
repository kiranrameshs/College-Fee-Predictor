package com.kiran.collegefunding.model.collegefunding;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class State {
    final int code;
    final String abbr;
    final String name;
}
