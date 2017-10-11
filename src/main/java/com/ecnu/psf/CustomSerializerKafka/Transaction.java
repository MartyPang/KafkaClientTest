package com.ecnu.psf.CustomSerializerKafka;


import jdk.nashorn.internal.objects.annotations.Constructor;

public class Transaction {
    private Long tx_id;
    private String tx_content;
    private String timestamp;

    public Transaction(){

    }

    public Transaction(Long tid, String tcontent, String timestamp){
        this.tx_id = tid;
        this.tx_content = tcontent;
        this.timestamp = timestamp;
    }

    public String toString(){
        return "Transaction [id="+this.tx_id+", content="+this.tx_content+", timestamp="+this.timestamp+"]";
    }

    //getters and setters

    public Long getTx_id() {
        return tx_id;
    }

    public void setTx_id(Long tx_id) {
        this.tx_id = tx_id;
    }

    public String getTx_content() {
        return tx_content;
    }

    public void setTx_content(String tx_content) {
        this.tx_content = tx_content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
