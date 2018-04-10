package pl.edu.uj.cenuj.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseAppConfig {

    @Bean
    public FirebaseApp firebaseApp(@Value("${firebase.token.path}") String firebaseTokenPath,
                                   @Value("${firebase.db.name}") String dbName) throws IOException {
        FileInputStream serviceAccount = new FileInputStream(firebaseTokenPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://" + dbName + ".firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
