import 'package:appFlutter/models/pessoa.dart';
import 'package:appFlutter/helpers/pessoa.dart';
import 'package:flutter/material.dart';

class PessoaEditPage extends StatefulWidget {
  PessoaEditPage({Key key, this.objeto}) : super(key: key);

  final Pessoa objeto;

  final PessoaHelper helper = PessoaHelper();

  @override
  _PessoaEditPageState createState() => _PessoaEditPageState();
}

class _PessoaEditPageState extends State<PessoaEditPage> {
  final _formKey = GlobalKey<FormState>();
  Pessoa obj;

  @override
  void initState() {
    super.initState();
    obj = widget.objeto;
  }

  void _salvar() {
    if (obj.id == null) {
      widget.helper
          .incluir(obj)
          .then((value) =>
              {Navigator.pop(context, 'Pessoa incluida com sucesso!')})
          .catchError((e) => {print(e)});
    } else {
      widget.helper
          .alterar(obj)
          .then((value) =>
              {Navigator.pop(context, 'Pessoa editada com sucesso!')})
          .catchError((e) => {print(e)});
    }
  }

  void _excluir() {
    widget.helper
        .excluir(obj.id)
        .then(
            (value) => {Navigator.pop(context, 'Pessoa excluida com sucesso!')})
        .catchError((e) => {print(e)});
  }

  void _cancelar() {
    Navigator.pop(context, 'Ação Cancelada!');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(obj.id == null ? 'Cadastro de Pessoa' : 'Edição de Pessoa'),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: EdgeInsets.all(10.0),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                TextFormField(
                  decoration: InputDecoration(
                      icon: Icon(Icons.person),
                      border: OutlineInputBorder(),
                      labelText: 'Nome'),
                  keyboardType: TextInputType.text,
                  maxLength: 50,
                  initialValue: obj.nome,
                  onChanged: (text) {
                    obj.nome = text;
                  },
                  validator: (value) {
                    if (value.isEmpty) {
                      return 'Você deve informar o nome da pessoa';
                    }
                    return null;
                  },
                ),
                TextFormField(
                  decoration: InputDecoration(
                      icon: Icon(Icons.mail),
                      border: OutlineInputBorder(),
                      labelText: 'Email'),
                  keyboardType: TextInputType.emailAddress,
                  maxLength: 80,
                  initialValue: obj.email,
                  onChanged: (text) {
                    obj.email = text;
                  },
                  validator: (value) {
                    if (value.isEmpty) {
                      return 'Informe o email';
                    }
                    if (!value.contains("@")) {
                      return 'Você deve informar um email válido';
                    }
                    return null;
                  },
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(vertical: 16.0),
                  child: Row(
                    children: [
                      RaisedButton.icon(
                        icon: Icon(Icons.save),
                        onPressed: () {
                          if (_formKey.currentState.validate()) {
                            _salvar();
                          }
                        },
                        label: Text('Salvar'),
                      ),
                      SizedBox(
                        width: 10,
                      ),
                      RaisedButton.icon(
                        icon: Icon(Icons.cancel),
                        onPressed: () {
                          _cancelar();
                        },
                        label: Text('Cancelar'),
                      ),
                      SizedBox(
                        width: 10,
                      ),
                      obj.id != null
                          ? RaisedButton.icon(
                              icon: Icon(Icons.delete),
                              onPressed: () {
                                _excluir();
                              },
                              label: Text('Excluir'),
                            )
                          : Container(),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
