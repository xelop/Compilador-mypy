pila segment stack 'stack'
    dw 256 dup (?)
pila ends

datos segment

    caca db 0    ; es un booleano
    enterox dw 0    ; es un entero
    enteroy dw 0    ; es un entero
    stringa db ""   ; es un string
datos ends

codigo segment
    assume cs:codigo, ds:datos, ss:pila

inicio:
    MOV ax, datos
    MOV ds, ax

    MOV ax,0
    CMP ax,0
    JNZ elseLabel6 ; ahora viene codigo de if
    JMP exitLabel7
    elseLabel6: ; ahora viene codigo de else
    exitLabel7: ;termina bloque if-else

    MOV ax,0
    CMP ax,0
    JNZ elseLabel8 ; ahora viene codigo de if
    JMP exitLabel9
    elseLabel8: ; ahora viene codigo de else
    exitLabel9: ;termina bloque if-else


    MOV ax, 4c00h
    INT 21h

funcion2 proc near
    ;guarda registros
    ;cuerpo de la función:

    MOV ax,0
    CMP ax,0
    JNZ elseLabel4 ; ahora viene codigo de if
    JMP exitLabel5
    elseLabel4: ; ahora viene codigo de else
    exitLabel5: ;termina bloque if-else

    ;saca registros
funcion2 endp

funcion1 proc near
    ;guarda registros
    ;cuerpo de la función:

    MOV ax,0
    CMP ax,0
    JNZ elseLabel0 ; ahora viene codigo de if 1
    MOV ax,0
    CMP ax,0
    JNZ elseLabel2 ; ahora viene codigo de if 2
    JMP exitLabel3
    elseLabel2: ; ahora viene codigo de else
    exitLabel3: ;termina bloque if-else
    JMP exitLabel1
    elseLabel0: ; ahora viene codigo de else
    exitLabel1: ;termina bloque if-else

    ;saca registros
funcion1 endp

codigo ends
end inicio
