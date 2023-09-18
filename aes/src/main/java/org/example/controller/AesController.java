package org.example.controller;


import org.example.dto.Request;
import org.example.dto.Response;
import org.example.service.AesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/aes")
public class AesController {

    @Autowired
    AesService aesService;


    @PostMapping(value="/encrypt")
    public ResponseEntity<?> encryptionOfText(@RequestBody Request request) throws Exception {
        try{
            if(request.getRequestText().equals("")){
                throw new Exception("Invalid text");
            }
            String cipher = aesService.encryption(request.getRequestText(), "text");
            Response resp = new Response(200, "Successfully encrypted the data","", cipher);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e){
            Response resp = new Response(500, "Failed to encrypt the data",e.getMessage(), "");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @PostMapping(value="/decrypt")
    public ResponseEntity<?> decryptionOfText(@RequestBody Request request) throws Exception {
        try{
            String text = aesService.decrypt(request.getRequestText(), "text");
            Response resp = new Response(200, "Successfully decrypted the data","", text);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e){
            Response resp = new Response(500, "Failed to decrypt the data",e.getMessage(), "");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @GetMapping(value="/encryptUrlParam")
    public ResponseEntity<?> encryptionOfUrlParam(@RequestParam String param) throws Exception {
        try{
            String text = aesService.encryption(param, "param");
            Response resp = new Response(200, "Successfully encrypted the url parameter","", text);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e){
            Response resp = new Response(500, "Failed to decrypt the url param",e.getMessage(), "");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }

    @GetMapping(value="/decryptUrlParam")
    public ResponseEntity<?> decryptionOfUrlParam(@RequestParam String param) throws Exception {
        try{
            String text = aesService.decrypt(param, "param");
            Response resp = new Response(200, "Successfully decrypted the url parameter","", text);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        }catch (Exception e){
            Response resp = new Response(500, "Failed to decrypt the url param",e.getMessage(), "");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
        }
    }
}
