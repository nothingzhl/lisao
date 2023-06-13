package org.zhl.disruptor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhanghanlin
 * @date 2023/6/9
 **/
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data implements Serializable {

    private Long value;

}
