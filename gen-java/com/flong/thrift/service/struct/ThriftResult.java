/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.flong.thrift.service.struct;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * **************************************************************************************************
 *  为ThriftResult添加数据不完全和内部错误两种类型
 *  枚举类型ThriftResult，表示返回结果，成功或失败，如果失败，还可以表示失败原因
 *  每种返回类型都对应一个封装的结构体，该结构体其命名遵循规则："Result" + "具体操作结果类型"，结构体都包含两部分内容：
 *  第一部分为枚举类型ThriftResult变量result，表示操作结果,可以 表示成功，或失败，失败时可以给出失败原因
 *  第二部分的变量名为value，表示返回结果的内容；
 * ***************************************************************************************************
 */
public enum ThriftResult implements org.apache.thrift.TEnum {
  SUCCESS(0),
  /**
   * 成功
   */
  SERVER_UNWORKING(1),
  /**
   * 服务器处于非Working状态
   */
  NO_CONTENT(2),
  /**
   * 请求结果不存在
   */
  PARAMETER_ERROR(3),
  /**
   * 参数错误
   */
  EXCEPTION(4),
  /**
   * 内部出现异常
   */
  INDEX_ERROR(5),
  /**
   * 错误的索引或者下标值
   */
  UNKNOWN_ERROR(6),
  /**
   * 未知错误
   */
  DATA_NOT_COMPLETE(7),
  /**
   * 数据不完全
   */
  INNER_ERROR(8);

  private final int value;

  private ThriftResult(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ThriftResult findByValue(int value) { 
    switch (value) {
      case 0:
        return SUCCESS;
      case 1:
        return SERVER_UNWORKING;
      case 2:
        return NO_CONTENT;
      case 3:
        return PARAMETER_ERROR;
      case 4:
        return EXCEPTION;
      case 5:
        return INDEX_ERROR;
      case 6:
        return UNKNOWN_ERROR;
      case 7:
        return DATA_NOT_COMPLETE;
      case 8:
        return INNER_ERROR;
      default:
        return null;
    }
  }
}
