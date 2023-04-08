# Neksus_no-docker

Před spuštěním
- zkontrolovat si připojení na ora1.uhk.cz
- pokud ping neuspěje, zkontroluj si připojení školní vpn

Instrukce
- aktualizuj maven dependencies
- spusť src/main/java/com.example.demo/DemoApplication
- otevři si libovolný prohlížeč
- localhost:8080
  - /hello
    - jednoduchej hello world test
    - ?name=...
      - GET request
  - /db
    - napojí na databázi a vypíše prvního zaměstnance
