import 'package:appFlutter/models/pessoa.dart';
import 'package:appFlutter/pages/pessoa/pessoas_edit.dart';
import 'package:appFlutter/helpers/pessoa.dart';
import 'package:appFlutter/util/dialogos.dart';
import 'package:flutter/material.dart';

class PessoaListPage extends StatefulWidget {
  PessoaListPage({Key key, this.title}) : super(key: key);

  final String title;

  // Utilizar a classe de serviço
  final PessoaHelper helper = PessoaHelper();

  @override
  _PessoaListPageState createState() => _PessoaListPageState();
}

class _PessoaListPageState extends State<PessoaListPage> {
  List<Pessoa> lista = new List();

  @override
  void initState() {
    super.initState();
    obterTodos();
  }

  void obterTodos() {
    widget.helper.obterTodos().then((value) => {
          setState(() {
            lista = value;
          })
        });
  }

  void _addPessoa() async {
    final res = await Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => PessoaEditPage(
          objeto: new Pessoa(),
        ),
      ),
    );
    obterTodos();
    Dialogos.showToastSuccess(res);
  }

  void selecionarPessoa(Pessoa p) async {
    final res = await Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => PessoaEditPage(
          objeto: p,
        ),
      ),
    );
    obterTodos();
    Dialogos.showToastSuccess(res);
  }

  void _excluir(Pessoa obj) {
    Dialogos.showConfirmDialog(
      context,
      'Você deseja realmente excluir ${obj.nome}?',
      () => {
        widget.helper
            .excluir(obj.id)
            .then((value) => {
                  Dialogos.showToastSuccess('Pessoa excluida com sucesso!'),
                  obterTodos()
                })
            .catchError(
              (e) => {
                print(e),
                Dialogos.showToastError(e.toString()),
              },
            )
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: ListView(
          padding: EdgeInsets.all(10.0),
          scrollDirection: Axis.vertical,
          children: lista
              .map(
                (data) => ListTile(
                  leading: Icon(Icons.person),
                  title: Text(data.nome),
                  onTap: () => selecionarPessoa(data),
                  trailing: PopupMenuButton(
                    onSelected: (value) {
                      if (value == 'editar') {
                        selecionarPessoa(data);
                      } else {
                        _excluir(data);
                      }
                    },
                    itemBuilder: (context) => [
                      PopupMenuItem(child: Text('Editar'), value: 'editar'),
                      PopupMenuItem(child: Text('Excluir'), value: 'excluir'),
                    ],
                  ),
                ),
              )
              .toList(),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addPessoa,
        tooltip: 'Adicionar Pessoa',
        child: Icon(
          Icons.person_add,
        ), // This trailing comma makes auto-formatting nicer for build methods.
      ),
    );
  }
}
