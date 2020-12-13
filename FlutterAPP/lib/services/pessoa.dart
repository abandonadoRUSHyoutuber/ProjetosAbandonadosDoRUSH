import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:appFlutter/models/pessoa.dart';

class PessoaServ {
  String apiUrl = "http://10.1.1.21:3000/pessoas";

  Future<List<Pessoa>> obterTodos() async {
    final response = await http.get(apiUrl);
    if (response.statusCode == 200) {
      final mapa = jsonDecode(response.body).cast<Map<String, dynamic>>();
      return mapa.map<Pessoa>((json) => Pessoa.fromJson(json)).toList();
    } else {
      throw Exception('Falha ao tentar carregar a lista de pessoas! Erro: ' +
          response.body);
    }
  }

  Future<void> alterar(Pessoa obj) async {
    final response = await http.put(apiUrl,
        body: jsonEncode(obj.toJson()),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8'
        });
    if (response.statusCode != 200) {
      throw Exception(
          'Falha ao tentar alterar a pessoa! Erro: ' + response.body);
    }
  }

  Future<void> incluir(Pessoa obj) async {
    final response = await http.post(apiUrl,
        body: jsonEncode(obj.toJson()),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8'
        });
    if (response.statusCode != 200) {
      throw Exception(
          'Falha ao tentar incluir a pessoa! Erro: ' + response.body);
    }
  }

  Future<void> excluir(Pessoa obj) async {
    final response = await http.delete(apiUrl + "/" + obj.id.toString(),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8'
        });
    if (response.statusCode != 200) {
      throw Exception(
          'Falha ao tentar excluir a pessoa! Erro: ' + response.body);
    }
  }
}
