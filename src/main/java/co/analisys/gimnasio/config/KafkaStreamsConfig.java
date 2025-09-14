package co.analisys.gimnasio.config;

import java.time.Duration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import co.analisys.gimnasio.model.DatosEntrenamiento;
import co.analisys.gimnasio.model.ResumenEntrenamiento;

@Configuration 
@EnableKafkaStreams
public class KafkaStreamsConfig { 

    @Bean
    public KStream<String, DatosEntrenamiento> kStream(StreamsBuilder streamsBuilder) {

        JsonSerde<DatosEntrenamiento> datosEntrenamientoSerde = new JsonSerde<>(DatosEntrenamiento.class);

        KStream<String, DatosEntrenamiento> stream = streamsBuilder.stream(
            "datos-entrenamiento",
            Consumed.with(Serdes.String(), datosEntrenamientoSerde) // 👈 Aquí defines explícitamente el serde
        );

        stream.groupByKey()
            .windowedBy(TimeWindows.of(Duration.ofDays(7)))
            .aggregate(
                ResumenEntrenamiento::new,
                (key, value, aggregate) -> aggregate.actualizar(value),
                Materialized.<String, ResumenEntrenamiento, WindowStore<org.apache.kafka.common.utils.Bytes, byte[]>>as("resumen-entrenamiento-store")
                    .withKeySerde(Serdes.String())
                    .withValueSerde(new JsonSerde<>(ResumenEntrenamiento.class)) // 👈 Aquí está el detalle
            )
            .toStream()
            .to("resumen-entrenamiento");

        return stream;
    }
} 

