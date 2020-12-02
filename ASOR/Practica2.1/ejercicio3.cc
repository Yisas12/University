#include <iostream>
#include <errno.h>
#include <string.h>
using namespace std;

int main() {

	for (int i = 0; i < 255; i++) {
		cout << strerror(i) << "\n";
	}
	return 1;
}
