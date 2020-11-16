#include<iostream>
using namespace std;

int resolver(int n, bool &yo) {
	if (n > 2) {
		if (n % 2 == 0) {
			if (!yo) {
				yo = true;
				return 1 + resolver(n - 1, yo);
			}
			else {
				yo = false;
				return 1 + resolver(n - 2, yo);
			}
		}
		else {
			if (!yo) yo = true;
			else yo = false;
			return 1 + resolver(n - 1, yo);
		}
	}
	else if(n == 1){
		return 1;
	}
	else if (n == 2) {
		if (!yo) {
			yo = true;
			return 1 + resolver(n - 1, yo);
		}
		else return 1;
	}
}

bool resuelveCaso() {
	int n;
	bool b = false;
	cin >> n;

	if (n == 0) return false;

	cout << resolver(n, b) << "\n";

	return true;
}

int main() {
	while (resuelveCaso());
	return 0;
}