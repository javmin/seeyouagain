package com.xmn.designer.base.thrift.tbase;
/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletRecord implements org.apache.thrift.TBase<WalletRecord, WalletRecord._Fields>, java.io.Serializable, Cloneable, Comparable<WalletRecord> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("WalletRecord");

  private static final org.apache.thrift.protocol.TField PAGE_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("pageCount", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField WALLET_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("walletList", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("count", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new WalletRecordStandardSchemeFactory());
    schemes.put(TupleScheme.class, new WalletRecordTupleSchemeFactory());
  }

  public int pageCount; // required
  public List<Map<String,String>> walletList; // required
  public int count; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PAGE_COUNT((short)1, "pageCount"),
    WALLET_LIST((short)2, "walletList"),
    COUNT((short)3, "count");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PAGE_COUNT
          return PAGE_COUNT;
        case 2: // WALLET_LIST
          return WALLET_LIST;
        case 3: // COUNT
          return COUNT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __PAGECOUNT_ISSET_ID = 0;
  private static final int __COUNT_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PAGE_COUNT, new org.apache.thrift.meta_data.FieldMetaData("pageCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.WALLET_LIST, new org.apache.thrift.meta_data.FieldMetaData("walletList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)))));
    tmpMap.put(_Fields.COUNT, new org.apache.thrift.meta_data.FieldMetaData("count", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(WalletRecord.class, metaDataMap);
  }

  public WalletRecord() {
  }

  public WalletRecord(
    int pageCount,
    List<Map<String,String>> walletList,
    int count)
  {
    this();
    this.pageCount = pageCount;
    setPageCountIsSet(true);
    this.walletList = walletList;
    this.count = count;
    setCountIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public WalletRecord(WalletRecord other) {
    __isset_bitfield = other.__isset_bitfield;
    this.pageCount = other.pageCount;
    if (other.isSetWalletList()) {
      List<Map<String,String>> __this__walletList = new ArrayList<Map<String,String>>(other.walletList.size());
      for (Map<String,String> other_element : other.walletList) {
        Map<String,String> __this__walletList_copy = new HashMap<String,String>(other_element);
        __this__walletList.add(__this__walletList_copy);
      }
      this.walletList = __this__walletList;
    }
    this.count = other.count;
  }

  public WalletRecord deepCopy() {
    return new WalletRecord(this);
  }

  @Override
  public void clear() {
    setPageCountIsSet(false);
    this.pageCount = 0;
    this.walletList = null;
    setCountIsSet(false);
    this.count = 0;
  }

  public int getPageCount() {
    return this.pageCount;
  }

  public WalletRecord setPageCount(int pageCount) {
    this.pageCount = pageCount;
    setPageCountIsSet(true);
    return this;
  }

  public void unsetPageCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAGECOUNT_ISSET_ID);
  }

  /** Returns true if field pageCount is set (has been assigned a value) and false otherwise */
  public boolean isSetPageCount() {
    return EncodingUtils.testBit(__isset_bitfield, __PAGECOUNT_ISSET_ID);
  }

  public void setPageCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAGECOUNT_ISSET_ID, value);
  }

  public int getWalletListSize() {
    return (this.walletList == null) ? 0 : this.walletList.size();
  }

  public java.util.Iterator<Map<String,String>> getWalletListIterator() {
    return (this.walletList == null) ? null : this.walletList.iterator();
  }

  public void addToWalletList(Map<String,String> elem) {
    if (this.walletList == null) {
      this.walletList = new ArrayList<Map<String,String>>();
    }
    this.walletList.add(elem);
  }

  public List<Map<String,String>> getWalletList() {
    return this.walletList;
  }

  public WalletRecord setWalletList(List<Map<String,String>> walletList) {
    this.walletList = walletList;
    return this;
  }

  public void unsetWalletList() {
    this.walletList = null;
  }

  /** Returns true if field walletList is set (has been assigned a value) and false otherwise */
  public boolean isSetWalletList() {
    return this.walletList != null;
  }

  public void setWalletListIsSet(boolean value) {
    if (!value) {
      this.walletList = null;
    }
  }

  public int getCount() {
    return this.count;
  }

  public WalletRecord setCount(int count) {
    this.count = count;
    setCountIsSet(true);
    return this;
  }

  public void unsetCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COUNT_ISSET_ID);
  }

  /** Returns true if field count is set (has been assigned a value) and false otherwise */
  public boolean isSetCount() {
    return EncodingUtils.testBit(__isset_bitfield, __COUNT_ISSET_ID);
  }

  public void setCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COUNT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PAGE_COUNT:
      if (value == null) {
        unsetPageCount();
      } else {
        setPageCount((Integer)value);
      }
      break;

    case WALLET_LIST:
      if (value == null) {
        unsetWalletList();
      } else {
        setWalletList((List<Map<String,String>>)value);
      }
      break;

    case COUNT:
      if (value == null) {
        unsetCount();
      } else {
        setCount((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PAGE_COUNT:
      return Integer.valueOf(getPageCount());

    case WALLET_LIST:
      return getWalletList();

    case COUNT:
      return Integer.valueOf(getCount());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PAGE_COUNT:
      return isSetPageCount();
    case WALLET_LIST:
      return isSetWalletList();
    case COUNT:
      return isSetCount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof WalletRecord)
      return this.equals((WalletRecord)that);
    return false;
  }

  public boolean equals(WalletRecord that) {
    if (that == null)
      return false;

    boolean this_present_pageCount = true;
    boolean that_present_pageCount = true;
    if (this_present_pageCount || that_present_pageCount) {
      if (!(this_present_pageCount && that_present_pageCount))
        return false;
      if (this.pageCount != that.pageCount)
        return false;
    }

    boolean this_present_walletList = true && this.isSetWalletList();
    boolean that_present_walletList = true && that.isSetWalletList();
    if (this_present_walletList || that_present_walletList) {
      if (!(this_present_walletList && that_present_walletList))
        return false;
      if (!this.walletList.equals(that.walletList))
        return false;
    }

    boolean this_present_count = true;
    boolean that_present_count = true;
    if (this_present_count || that_present_count) {
      if (!(this_present_count && that_present_count))
        return false;
      if (this.count != that.count)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(WalletRecord other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPageCount()).compareTo(other.isSetPageCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageCount, other.pageCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWalletList()).compareTo(other.isSetWalletList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWalletList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.walletList, other.walletList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCount()).compareTo(other.isSetCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.count, other.count);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("WalletRecord(");
    boolean first = true;

    sb.append("pageCount:");
    sb.append(this.pageCount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("walletList:");
    if (this.walletList == null) {
      sb.append("null");
    } else {
      sb.append(this.walletList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("count:");
    sb.append(this.count);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class WalletRecordStandardSchemeFactory implements SchemeFactory {
    public WalletRecordStandardScheme getScheme() {
      return new WalletRecordStandardScheme();
    }
  }

  private static class WalletRecordStandardScheme extends StandardScheme<WalletRecord> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, WalletRecord struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PAGE_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageCount = iprot.readI32();
              struct.setPageCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WALLET_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.walletList = new ArrayList<Map<String,String>>(_list0.size);
                for (int _i1 = 0; _i1 < _list0.size; ++_i1)
                {
                  Map<String,String> _elem2;
                  {
                    org.apache.thrift.protocol.TMap _map3 = iprot.readMapBegin();
                    _elem2 = new HashMap<String,String>(2*_map3.size);
                    for (int _i4 = 0; _i4 < _map3.size; ++_i4)
                    {
                      String _key5;
                      String _val6;
                      _key5 = iprot.readString();
                      _val6 = iprot.readString();
                      _elem2.put(_key5, _val6);
                    }
                    iprot.readMapEnd();
                  }
                  struct.walletList.add(_elem2);
                }
                iprot.readListEnd();
              }
              struct.setWalletListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.count = iprot.readI32();
              struct.setCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, WalletRecord struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PAGE_COUNT_FIELD_DESC);
      oprot.writeI32(struct.pageCount);
      oprot.writeFieldEnd();
      if (struct.walletList != null) {
        oprot.writeFieldBegin(WALLET_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.MAP, struct.walletList.size()));
          for (Map<String,String> _iter7 : struct.walletList)
          {
            {
              oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, _iter7.size()));
              for (Map.Entry<String, String> _iter8 : _iter7.entrySet())
              {
                oprot.writeString(_iter8.getKey());
                oprot.writeString(_iter8.getValue());
              }
              oprot.writeMapEnd();
            }
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(COUNT_FIELD_DESC);
      oprot.writeI32(struct.count);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class WalletRecordTupleSchemeFactory implements SchemeFactory {
    public WalletRecordTupleScheme getScheme() {
      return new WalletRecordTupleScheme();
    }
  }

  private static class WalletRecordTupleScheme extends TupleScheme<WalletRecord> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, WalletRecord struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetPageCount()) {
        optionals.set(0);
      }
      if (struct.isSetWalletList()) {
        optionals.set(1);
      }
      if (struct.isSetCount()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetPageCount()) {
        oprot.writeI32(struct.pageCount);
      }
      if (struct.isSetWalletList()) {
        {
          oprot.writeI32(struct.walletList.size());
          for (Map<String,String> _iter9 : struct.walletList)
          {
            {
              oprot.writeI32(_iter9.size());
              for (Map.Entry<String, String> _iter10 : _iter9.entrySet())
              {
                oprot.writeString(_iter10.getKey());
                oprot.writeString(_iter10.getValue());
              }
            }
          }
        }
      }
      if (struct.isSetCount()) {
        oprot.writeI32(struct.count);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, WalletRecord struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.pageCount = iprot.readI32();
        struct.setPageCountIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list11 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.MAP, iprot.readI32());
          struct.walletList = new ArrayList<Map<String,String>>(_list11.size);
          for (int _i12 = 0; _i12 < _list11.size; ++_i12)
          {
            Map<String,String> _elem13;
            {
              org.apache.thrift.protocol.TMap _map14 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
              _elem13 = new HashMap<String,String>(2*_map14.size);
              for (int _i15 = 0; _i15 < _map14.size; ++_i15)
              {
                String _key16;
                String _val17;
                _key16 = iprot.readString();
                _val17 = iprot.readString();
                _elem13.put(_key16, _val17);
              }
            }
            struct.walletList.add(_elem13);
          }
        }
        struct.setWalletListIsSet(true);
      }
      if (incoming.get(2)) {
        struct.count = iprot.readI32();
        struct.setCountIsSet(true);
      }
    }
  }

}

