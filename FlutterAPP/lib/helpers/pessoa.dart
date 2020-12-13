import 'dart:async';
import 'package:appFlutter/models/pessoa.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';

final String pessoasTable = "pessoas";
final String id = "id";
final String nome = "nome";
final String email = "email";

class PessoaHelper {
  static final PessoaHelper _instance = PessoaHelper.internal();
  factory PessoaHelper() => _instance;
  PessoaHelper.internal();
  Database _db;

  Future<Database> get db async {
    if (_db != null) {
      return _db;
    } else {
      _db = await initDB();
      return _db;
    }
  }

  Future<Database> initDB() async {
    final databasesPath = await getDatabasesPath();
    final path = join(databasesPath, "pessoas.db");
    return await openDatabase(
      path,
      version: 1,
      onCreate: (Database db, int newerVersion) async {
        await db.execute(
          "CREATE TABLE $pessoasTable ($id integer PRIMARY KEY, $nome TEXT, $email TEXT)",
        );
      },
    );
  }

  Future<Pessoa> incluir(Pessoa pessoa) async {
    Database dbPessoa = await db;
    pessoa.id = await dbPessoa.insert(pessoasTable, pessoa.toMap());
    return pessoa;
  }

  Future<Pessoa> getObjeto(int idFind) async {
    Database dbPessoa = await db;
    List<Map> maps = await dbPessoa.query(pessoasTable,
        columns: [id, nome], where: "$id = ?", whereArgs: [idFind]);
    if (maps.length > 0) {
      return Pessoa.fromMap(maps.first);
    } else {
      return null;
    }
  }

  Future<int> excluir(int idDel) async {
    Database dbPessoa = await db;
    return await dbPessoa
        .delete(pessoasTable, where: "$id = ?", whereArgs: [idDel]);
  }

  Future<int> alterar(Pessoa pessoa) async {
    Database dbPessoa = await db;
    return await dbPessoa.update(pessoasTable, pessoa.toMap(),
        where: "$id = ?", whereArgs: [pessoa.id]);
  }

  Future<List> obterTodos() async {
    Database dbPessoa = await db;
    List listMap = await dbPessoa.rawQuery("SELECT * FROM $pessoasTable");
    List<Pessoa> listPessoas = List();
    for (Map m in listMap) {
      listPessoas.add(Pessoa.fromMap(m));
    }
    return listPessoas;
  }
}
