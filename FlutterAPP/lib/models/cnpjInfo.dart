import 'dart:convert';

class CnpjInfo {
  CnpjInfo({
    this.status,
    this.atividadePrincipal,
    this.dataSituacao,
    this.complemento,
    this.tipo,
    this.nome,
    this.uf,
    this.telefone,
    this.atividadesSecundarias,
    this.qsa,
    this.situacao,
    this.bairro,
    this.logradouro,
    this.numero,
    this.cep,
    this.municipio,
    this.porte,
    this.abertura,
    this.naturezaJuridica,
    this.fantasia,
    this.cnpj,
    this.ultimaAtualizacao,
    this.email,
    this.efr,
    this.motivoSituacao,
    this.situacaoEspecial,
    this.dataSituacaoEspecial,
    this.capitalSocial,
    this.extra,
    this.billing,
  });

  String status;
  List<Atividade> atividadePrincipal;
  String dataSituacao;
  String complemento;
  String tipo;
  String nome;
  String uf;
  String telefone;
  List<Atividade> atividadesSecundarias;
  List<Qsa> qsa;
  String situacao;
  String bairro;
  String logradouro;
  String numero;
  String cep;
  String municipio;
  String porte;
  String abertura;
  String naturezaJuridica;
  String fantasia;
  String cnpj;
  DateTime ultimaAtualizacao;
  String email;
  String efr;
  String motivoSituacao;
  String situacaoEspecial;
  String dataSituacaoEspecial;
  String capitalSocial;
  Extra extra;
  Billing billing;

  factory CnpjInfo.fromJson(String str) => CnpjInfo.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory CnpjInfo.fromMap(Map<String, dynamic> json) => CnpjInfo(
      status: json["status"],
      atividadePrincipal: json["atividade_principal"] == null
          ? null
          : List<Atividade>.from(
              json["atividade_principal"].map((x) => Atividade.fromMap(x))),
      dataSituacao: json["data_situacao"],
      complemento: json["complemento"],
      tipo: json["tipo"],
      nome: json["nome"],
      uf: json["uf"],
      telefone: json["telefone"],
      atividadesSecundarias: json["atividades_secundarias"] == null
          ? null
          : List<Atividade>.from(
              json["atividades_secundarias"].map((x) => Atividade.fromMap(x))),
      qsa: json["qsa"] == null
          ? null
          : List<Qsa>.from(json["qsa"].map((x) => Qsa.fromMap(x))),
      situacao: json["situacao"],
      bairro: json["bairro"],
      logradouro: json["logradouro"],
      numero: json["numero"],
      cep: json["cep"],
      municipio: json["municipio"],
      porte: json["porte"],
      abertura: json["abertura"],
      naturezaJuridica: json["natureza_juridica"],
      fantasia: json["fantasia"],
      cnpj: json["cnpj"],
      ultimaAtualizacao: json["ultima_atualizacao"] == null
          ? null
          : DateTime.parse(json["ultima_atualizacao"]),
      email: json["email"],
      efr: json["efr"],
      motivoSituacao: json["motivo_situacao"],
      situacaoEspecial: json["situacao_especial"],
      dataSituacaoEspecial: json["data_situacao_especial"],
      capitalSocial: json["capital_social"]);

  Map<String, dynamic> toMap() => {
        "status": status,
        "atividade_principal":
            List<dynamic>.from(atividadePrincipal.map((x) => x.toMap())),
        "data_situacao": dataSituacao,
        "complemento": complemento,
        "tipo": tipo,
        "nome": nome,
        "uf": uf,
        "telefone": telefone,
        "atividades_secundarias":
            List<dynamic>.from(atividadesSecundarias.map((x) => x.toMap())),
        "qsa": List<dynamic>.from(qsa.map((x) => x.toMap())),
        "situacao": situacao,
        "bairro": bairro,
        "logradouro": logradouro,
        "numero": numero,
        "cep": cep,
        "municipio": municipio,
        "porte": porte,
        "abertura": abertura,
        "natureza_juridica": naturezaJuridica,
        "fantasia": fantasia,
        "cnpj": cnpj,
        "ultima_atualizacao": ultimaAtualizacao.toIso8601String(),
        "email": email,
        "efr": efr,
        "motivo_situacao": motivoSituacao,
        "situacao_especial": situacaoEspecial,
        "data_situacao_especial": dataSituacaoEspecial,
        "capital_social": capitalSocial,
        "extra": extra.toMap(),
        "billing": billing.toMap(),
      };
}

class Atividade {
  Atividade({
    this.text,
    this.code,
  });

  String text;
  String code;

  factory Atividade.fromJson(String str) => Atividade.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Atividade.fromMap(Map<String, dynamic> json) => Atividade(
        text: json["text"],
        code: json["code"],
      );

  Map<String, dynamic> toMap() => {
        "text": text,
        "code": code,
      };
}

class Billing {
  Billing({
    this.free,
    this.database,
  });

  bool free;
  bool database;

  factory Billing.fromJson(String str) => Billing.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Billing.fromMap(Map<String, dynamic> json) => Billing(
        free: json["free"],
        database: json["database"],
      );

  Map<String, dynamic> toMap() => {
        "free": free,
        "database": database,
      };
}

class Extra {
  Extra();

  factory Extra.fromJson(String str) => Extra.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Extra.fromMap(Map<String, dynamic> json) => Extra();

  Map<String, dynamic> toMap() => {};
}

class Qsa {
  Qsa({
    this.qual,
    this.nome,
  });

  String qual;
  String nome;

  factory Qsa.fromJson(String str) => Qsa.fromMap(json.decode(str));

  String toJson() => json.encode(toMap());

  factory Qsa.fromMap(Map<String, dynamic> json) => Qsa(
        qual: json["qual"],
        nome: json["nome"],
      );

  Map<String, dynamic> toMap() => {
        "qual": qual,
        "nome": nome,
      };
}
