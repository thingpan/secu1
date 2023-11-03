package com.test.secu1.common.util;

public class CustomList<E> {

	   private Object[] strs = new Object[0];
	   
	   public int size() {
	      return strs.length;
	   }
	   public boolean remove(int idx) {//3
	      Object[] tmpStrs = strs;
	      strs = new Object[strs.length-1];
	      for(int i=0;i<idx;i++) {
	         strs[i] = tmpStrs[i];
	      }
	      for(int i=idx+1;i<tmpStrs.length;i++) {
	         strs[i-1] = tmpStrs[i];
	      }
	      return true;
	   }
	   public boolean add(E str) {
	      Object[] tmpStrs = strs;
	      strs = new Object[strs.length+1];
	      for(int i=0;i<tmpStrs.length;i++) {
	         strs[i] = tmpStrs[i];
	      }
	      strs[strs.length-1] = str;
	      return true;
	   }
	   public E get(int idx) {
	      return (E)strs[idx];
	   }
	   
	   public String toString() {
	      String pStr = "[ ";
	      for(Object str : strs) {
	         pStr += str + ",";
	      }
	      return pStr.substring(0, pStr.length()-1) + "]";
	   }
	   public static void main(String[] args) {
		CustomList<String> c =new CustomList<String>();
		c.add("1");
		CustomList<Integer> c1 =new CustomList<Integer>();
		c1.add(1);
				}
	   
	}
