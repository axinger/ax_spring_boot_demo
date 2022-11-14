package com.axing.demo;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class EnumTest {


    @Getter
    enum Week {
        NONE("", -1),
        ONE("一", 1),
        TWO("二", 2),

        ;

        private String name;
        private Integer code;

        Week(String name, Integer code) {
            this.name = name;
            this.code = code;
        }


        public static Week fromCode(Integer code) {
            return Arrays.stream(Week.values())
                    .filter(val -> val.getCode() == code)
                    .findFirst()
                    .orElse(NONE);
        }

        /**
         * 枚举通用取值
         *
         * @param func
         * @param name
         * @param <R>
         * @return
         */
        public static <R> Week from(Func1<R, ?> func, Object name) {
            String fieldName = LambdaUtil.getFieldName(func);
            return Arrays.stream(Week.values())
                    .filter(val -> ObjectUtil.equals(ReflectUtil.getFieldValue(val, fieldName), name))
                    .findFirst()
                    .orElse(NONE);
        }

    }


    @Test
    public void test1() {


        System.out.println("Week.ONE = " + Week.ONE);
        System.out.println("Week.ONE.name() = " + Week.ONE.name());
        System.out.println("Week.ONE.ordinal() = " + Week.ONE.ordinal());
        System.out.println("Week.valueOf(1) = " + Week.valueOf("ONE"));

        System.out.println("Week.fromCode(1) = " + Week.fromCode(1));
        System.out.println("Week.fromCode(0) = " + Week.fromCode(0));

        System.out.println("Week.from(Week::getDescription,\"1\") = " + Week.from(Week::getName, "1"));

        System.out.println("Week.from(Week::getDescription, \"一\") = " + Week.from(Week::getName, "一"));


        System.out.println("Week.from(Week::getCode, 1) = " + Week.from(Week::getCode, 1));
    }


}

