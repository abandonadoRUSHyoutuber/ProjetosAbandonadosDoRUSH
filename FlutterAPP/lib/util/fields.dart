import 'package:flutter/material.dart';

TextFormField textFormField(
    var controller, var labeltext, hinttext, TextInputType keytap, int length,
    {size: 20.0, validator, value}) {
  return TextFormField(
      controller: controller,
      validator: validator,
      keyboardType: keytap,
      maxLength: length,
      style: TextStyle(color: Colors.black),
      decoration: InputDecoration(
          border: new OutlineInputBorder(
            borderRadius: const BorderRadius.all(
              const Radius.circular(10.0),
            ),
          ),
          labelText: labeltext,
          labelStyle: TextStyle(fontSize: size, color: Colors.black),
          hintText: hinttext));
}
