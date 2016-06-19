pila segment stack 'stack'
    dw 256 dup (?)
pila ends

datos segment

datos ends
    caca db 0    ; es un booleano
    bool1 db 0    ; es un booleano
    bool2 db 0    ; es un booleano
    flotante dq 0.0 ; es un float
    charv db ''     ; es un char
    enterox dw 0    ; es un entero
    enteroy dw 0    ; es un entero
    stringa db ""   ; es un string

codigo segment
    assume cs:codigo, ds:datos, ss:pila

inicio
    mov ax, datos
    mov ds, ax

    CMP 0,0
    JNZ elseLabel6 ; ahora viene codigo de if
    JMP exitLabel7
    elseLabel6: ; ahora viene codigo de else
    exitLabel7: ;termina bloque if-else

    CMP 0,0
    JNZ elseLabel8 ; ahora viene codigo de if
    JMP exitLabel9
    elseLabel8: ; ahora viene codigo de else
    exitLabel9: ;termina bloque if-else


    mov ax, 4c00h
    int 21h

funcion2 proc near
    pusha
    ;cuerpo de la función

    CMP 0,0
    JNZ elseLabel4 ; ahora viene codigo de if
    JMP exitLabel5
    elseLabel4: ; ahora viene codigo de else
    exitLabel5: ;termina bloque if-else

    popa
funcion2 endp

funcion1 proc near
    pusha
    ;cuerpo de la función

    CMP 0,0
    JNZ elseLabel0 ; ahora viene codigo de if
    JMP exitLabel1
    elseLabel0: ; ahora viene codigo de else
    exitLabel1: ;termina bloque if-else
    CMP 0,0
    JNZ elseLabel2 ; ahora viene codigo de if
    JMP exitLabel3
    elseLabel2: ; ahora viene codigo de else
    exitLabel3: ;termina bloque if-else

    popa
funcion1 endp

codigo ends
end inicio
