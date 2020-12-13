import 'dart:convert';

class PlacaInfo {
  PlacaInfo({
    this.ano,
    this.anoModelo,
    this.chassi,
    this.codigoRetorno,
    this.codigoSituacao,
    this.cor,
    this.data,
    this.dataAtualizacaoAlarme,
    this.dataAtualizacaoCaracteristicasVeiculo,
    this.dataAtualizacaoRouboFurto,
    this.marca,
    this.mensagemRetorno,
    this.modelo,
    this.municipio,
    this.placa,
    this.situacao,
    this.uf,
    this.message,
  });

  String ano;
  String anoModelo;
  String chassi;
  String codigoRetorno;
  String codigoSituacao;
  String cor;
  String data;
  dynamic dataAtualizacaoAlarme;
  dynamic dataAtualizacaoCaracteristicasVeiculo;
  dynamic dataAtualizacaoRouboFurto;
  String marca;
  String mensagemRetorno;
  String modelo;
  String municipio;
  String placa;
  String situacao;
  String uf;
  String message;

  factory PlacaInfo.fromJson(String str) => PlacaInfo.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory PlacaInfo.fromMap(Map<String, dynamic> json) => PlacaInfo(
        ano: json["ano"],
        anoModelo: json["anoModelo"],
        chassi: json["chassi"],
        codigoRetorno: json["codigoRetorno"],
        codigoSituacao: json["codigoSituacao"],
        cor: json["cor"],
        data: json["data"],
        dataAtualizacaoAlarme: json["dataAtualizacaoAlarme"],
        dataAtualizacaoCaracteristicasVeiculo:
            json["dataAtualizacaoCaracteristicasVeiculo"],
        dataAtualizacaoRouboFurto: json["dataAtualizacaoRouboFurto"],
        marca: json["marca"],
        mensagemRetorno: json["mensagemRetorno"],
        modelo: json["modelo"],
        municipio: json["municipio"],
        placa: json["placa"],
        situacao: json["situacao"],
        uf: json["uf"],
        message: json["message"],
      );

  Map<String, dynamic> toMap() => {
        "ano": ano,
        "anoModelo": anoModelo,
        "chassi": chassi,
        "codigoRetorno": codigoRetorno,
        "codigoSituacao": codigoSituacao,
        "cor": cor,
        "data": data,
        "dataAtualizacaoAlarme": dataAtualizacaoAlarme,
        "dataAtualizacaoCaracteristicasVeiculo":
            dataAtualizacaoCaracteristicasVeiculo,
        "dataAtualizacaoRouboFurto": dataAtualizacaoRouboFurto,
        "marca": marca,
        "mensagemRetorno": mensagemRetorno,
        "modelo": modelo,
        "municipio": municipio,
        "placa": placa,
        "situacao": situacao,
        "uf": uf,
        "message": message,
      };
}
