package com.medicto.medicto.model;

import java.util.ArrayList;

public class InstructionModelToDB {

 public String name;



 public String amount;
 public String count;
 public String day;
 public ArrayList<String> timingList;
 public String timing;
 public ArrayList<String> effectList;
 public String time;
 public  String pUid;


 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getAmount() {
  return this.amount;
 }

 public String getCount() {
  return this.count;
 }

 public String getDay() {
  return this.day;
 }

 public ArrayList<String> getTimingList() {
  return this.timingList;
 }

 public String getTiming() {
  return this.timing;
 }

 public ArrayList<String> getEffectList() {
  return this.effectList;
 }

 public String getTime() {
  return this.time;
 }

 public String getpUid() {
  return this.pUid;
 }

 public void setAmount(String amount) {
  this.amount = amount;
 }

 public void setCount(String count) {
  this.count = count;
 }

 public void setDay(String day) {
  this.day = day;
 }

 public void setTimingList(ArrayList<String> timingList) {
  this.timingList = timingList;
 }

 public void setTiming(String timing) {
  this.timing = timing;
 }

 public void setEffectList(ArrayList<String> effectList) {
  this.effectList = effectList;
 }

 public void setTime(String time) {
  this.time = time;
 }

 public void setpUid(String pUid) {
  this.pUid = pUid;
 }
}
