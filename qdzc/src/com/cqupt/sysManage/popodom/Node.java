package com.cqupt.sysManage.popodom;


public class Node {  
 
 public int id;  
 public String text;  
 public int parentId;  
 private Children children = new Children();  
   
 public String toString() {    
  String result = "{ id: '"+id+"',  text : '"+text+"'";  
    
  if (children != null && children.getSize() != 0) {  
   result += ", children : " + children.toString();  
  } else {  
   result += "";  
  }  
      
  return result + "}";  
 }  
   
   
 // ��Ӻ��ӽڵ�  
 public void addChild(Node node) {  
  this.children.addChild(node);  
 }  
}  
