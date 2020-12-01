#include <iostream>
#include <stdio.h>
#include <time.h>
#include <sys/time.h>
using namespace std;

int main() {

	struct timeval c;
	gettimeofday(&c, NULL);

	for (int i = 0; i < 1000000; i++) {
		c.tv_usec++;
	}

	cout << "ms = c.tv_usec" << '\n';

	return 1;
}