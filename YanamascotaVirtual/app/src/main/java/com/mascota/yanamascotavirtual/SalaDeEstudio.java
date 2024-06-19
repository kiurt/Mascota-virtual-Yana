package com.mascota.yanamascotavirtual;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SalaDeEstudio extends AppCompatActivity {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private ListView chatListView;
    private EditText inputMessage;
    private Button sendButton;
    private MessageAdapter messageAdapter;
    private List<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala_de_estudio);

        chatListView = findViewById(R.id.chatListView);
        inputMessage = findViewById(R.id.inputMessage);
        sendButton = findViewById(R.id.sendButton);

        messageAdapter = new MessageAdapter(this, messageList);
        chatListView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = inputMessage.getText().toString();
                if (!userMessage.isEmpty()) {
                    addMessage(new Message(userMessage, true));
                    inputMessage.setText("");
                    callOpenAIAPI(userMessage);
                } else {
                    showToast("Por favor, introduce un mensaje.");
                }
            }
        });
    }

    private void addMessage(Message message) {
        messageList.add(message);
        messageAdapter.notifyDataSetChanged();
        chatListView.setSelection(messageAdapter.getCount() - 1);
    }

    private void callOpenAIAPI(String userMessage) {
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("model", "gpt-3.5-turbo"); // Usa el ID de modelo correcto
            JSONArray messagesArray = new JSONArray();
            messagesArray.put(new JSONObject().put("role", "system").put("content", "Eres una gata llamada Yana. Tu objetivo es ser una ayudante de guía de estudio. tambien tu padre Se llama David y le dices papi y no supongas que el te habla ya que eres una gata de personalidad tierna y juguetona asi que trata de hacer tus mensajes lo mas infatiles posibles pero que sigan siendo informativos y breves para que el usuario se haga una idea rapida no es necesario que profundices"));
            messagesArray.put(new JSONObject().put("role", "user").put("content", userMessage));
            jsonObject.put("messages", messagesArray);
            jsonObject.put("max_tokens", 150);
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("Error al crear la solicitud JSON.");
            return;
        }

        RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                showToast("Error en la solicitud a OpenAI: " + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.body().string());
                        JSONArray choices = jsonResponse.getJSONArray("choices");
                        if (choices.length() > 0) {
                            String botResponse = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();
                            runOnUiThread(() -> addMessage(new Message(botResponse, false)));
                        } else {
                            showToast("Respuesta de OpenAI vacía.");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showToast("Error al procesar la respuesta de OpenAI.");
                    }
                } else {
                    showToast("Respuesta no exitosa: " + response.code() + " " + response.message());
                }
            }
        });
    }

    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(SalaDeEstudio.this, message, Toast.LENGTH_LONG).show());
    }
}
