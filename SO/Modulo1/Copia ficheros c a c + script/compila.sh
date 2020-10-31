#Jesus Cencerrado Pedrero
#!/bin/bash

if (gcc copia.c -o copia 2>error.txt) ;
then 
./copia
echo $?

else
echo "Error"
fi

