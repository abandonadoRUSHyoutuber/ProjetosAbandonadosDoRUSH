import 'dart:convert';

class CepInfo {
  CepInfo({
    this.erro,
    this.cep,
    this.logradouro,
    this.complemento,
    this.bairro,
    this.localidade,
    this.uf,
    this.ibge,
    this.gia,
    this.ddd,
    this.siafi,
  });

  bool erro;
  String cep;
  String logradouro;
  String complemento;
  String bairro;
  String localidade;
  String uf;
  String ibge;
  String gia;
  String ddd;
  String siafi;

  factory CepInfo.fromJson(String str) => CepInfo.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory CepInfo.fromMap(Map<String, dynamic> json) => CepInfo(
        erro: json["erro"] == null ? null : json["erro"],
        cep: json["cep"] == null ? null : json["cep"],
        logradouro: json["logradouro"] == null ? null : json["logradouro"],
        complemento: json["complemento"] == null ? null : json["complemento"],
        bairro: json["bairro"] == null ? null : json["bairro"],
        localidade: json["localidade"] == null ? null : json["localidade"],
        uf: json["uf"] == null ? null : json["uf"],
        ibge: json["ibge"] == null ? null : json["ibge"],
        gia: json["gia"] == null ? null : json["gia"],
        ddd: json["ddd"] == null ? null : json["ddd"],
        siafi: json["siafi"] == null ? null : json["siafi"],
      );

  Map<String, dynamic> toMap() => {
        "erro": erro == null ? null : erro,
        "cep": cep == null ? null : cep,
        "logradouro": logradouro == null ? null : logradouro,
        "complemento": complemento == null ? null : complemento,
        "bairro": bairro == null ? null : bairro,
        "localidade": localidade == null ? null : localidade,
        "uf": uf == null ? null : uf,
        "ibge": ibge == null ? null : ibge,
        "gia": gia == null ? null : gia,
        "ddd": ddd == null ? null : ddd,
        "siafi": siafi == null ? null : siafi,
      };
}
