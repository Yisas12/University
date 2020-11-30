
#include <iostream>
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
using namespace std;

int main() {
	if (setuid(2) == -1) {
		perror("setuid no valido");
	}
	return 1;
}