#include <unistd.h>
#include <iostream>
using namespace std;

int main() {

	cout << pathconf("/", _PC_LINK_MAX) << '\n';
	cout << pathconf("/", _PC_PATH_MAX) << '\n';
	cout << pathconf("/", _PC_NAME_MAX) << '\n';
	return 1;
}
