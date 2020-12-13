class Pessoa {
  int id;
  String nome;
  String email;

  Pessoa({this.id, this.nome, this.email});
  Pessoa.initId(this.id);
  Pessoa.initAll(this.id, this.nome, this.email);

  Map<String, dynamic> toMap() {
    var map = <String, dynamic>{
      'id': id,
      'nome': nome,
      'email': email,
    };
    return map;
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'nome': nome,
        'email': email,
      };

  Pessoa.fromMap(Map<String, dynamic> map) {
    id = map['id'];
    nome = map['nome'];
    email = map['email'];
  }

  factory Pessoa.fromJson(Map<String, dynamic> json) {
    return Pessoa.initAll(json['_id'], json['nome'], json['email']);
  }
}
