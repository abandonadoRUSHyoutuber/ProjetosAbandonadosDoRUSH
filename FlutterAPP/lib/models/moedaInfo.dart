import 'dart:convert';

class MoedaInfo {
  MoedaInfo({
    this.code,
    this.codein,
    this.name,
    this.high,
    this.low,
    this.varBid,
    this.pctChange,
    this.bid,
    this.ask,
    this.timestamp,
    this.createDate,
  });

  String code;
  String codein;
  String name;
  String high;
  String low;
  String varBid;
  String pctChange;
  String bid;
  String ask;
  String timestamp;
  DateTime createDate;

  factory MoedaInfo.fromJson(String str) => MoedaInfo.fromMap(
      json.decode(str.replaceAll("[", "").replaceAll("]", "")));

  String toJson() => json.encode(toMap());

  factory MoedaInfo.fromMap(Map<String, dynamic> json) => MoedaInfo(
        code: json["code"],
        codein: json["codein"],
        name: json["name"],
        high: json["high"],
        low: json["low"],
        varBid: json["varBid"],
        pctChange: json["pctChange"],
        bid: json["bid"],
        ask: json["ask"],
        timestamp: json["timestamp"],
        createDate: DateTime.parse(json["create_date"]),
      );

  Map<String, dynamic> toMap() => {
        "code": code,
        "codein": codein,
        "name": name,
        "high": high,
        "low": low,
        "varBid": varBid,
        "pctChange": pctChange,
        "bid": bid,
        "ask": ask,
        "timestamp": timestamp,
        "create_date": createDate.toIso8601String(),
      };
}
