package mamba.rush.app.client.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mamba.rush.app.client.R;
import mamba.rush.app.client.services.BackgroundService;
import mamba.rush.app.client.utils.Log;
import mamba.rush.app.client.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), BackgroundService.class));
    }

    public void tryConnection(View v) {
        String serverIp = Utils.decodeIp(textField.getText().toString());
        if (!Utils.hasInternet(this)) {
            showInternetError();
            return;
        }

        if (!Utils.validateIp(serverIp)) {
            showInvalidIpError();
            return;
        }

        Utils.hideKeyboard(v.getContext(), textField);
        new Task(v.getContext()).execute();
    }

    public void showHelp(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton("OK, ENTENDI!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setIcon(R.drawable.help);
        dialog.setTitle("AJUDA");
        dialog.setMessage(
                "Oque é o código do servidor?\n" + "O código do servidor é um código unico que estabele a conexão entre o aplicativo e o servidor.\n\n" +
                        "Como encontrar o código do servidor?\n" + "O código do servidor pode ser encontrado dentro do servidor utilizando o comando /f app");
        dialog.show();
    }

    public void showInvalidIpError() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton("OK, ENTENDI!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setIcon(R.drawable.error);
        dialog.setTitle("ERRO");
        dialog.setMessage("O código do servidor não é um código valido!");
        dialog.show();
    }

    public void showConnectionError() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton("OK, ENTENDI!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setIcon(R.drawable.error);
        dialog.setTitle("ERRO DE CONEXÃO");
        dialog.setMessage("Falha ao tentar se conectar com o servidor!");
        dialog.show();
    }

    public void showInternetError() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton("OK, ENTENDI!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setIcon(R.drawable.error);
        dialog.setTitle("ERRO DE CONEXÃO");
        dialog.setMessage("Você precisa estar conectado a internet para poder efetuar a conexão!");
        dialog.show();
    }

    private class Task extends AsyncTask<Void, Void, Boolean> {

        private ProgressDialog dialog;
        private Context context;

        public Task(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Conectando-se ao servidor...");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try
            {
                String IP = Utils.decodeIp(textField.getText().toString());
                Socket socket = new Socket(IP, 56789);
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                JSONObject request = new JSONObject();
                request.put("type", 0);
                output.writeObject(request.toString());

                for (;;) {
                    JSONObject answer = new JSONObject(input.readObject().toString());
                    if (answer.getInt("type") == 0) return true;
                }

            } catch (IOException e) {
                return false;
            } catch (Throwable e) {
                Log.create(e);
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean sucess) {
            dialog.dismiss();
            if (!sucess) {
                showConnectionError();
            } else {
                setContentView(R.layout.activity_faction_input);
            }
        }
    }

}