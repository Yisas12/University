#include<iostream>
using namespace std;

int fact(const int &n) {
	/*int ant = n - 1;
	int res = n;*/
	
	if (n == 0) return 1;
	else if (n == 1) return 1;
	else if (n == 2) return 2;
	else if (n == 3) return 6;
	else if (n == 4) return 4;
	/*if (n < 5) {
		while (ant != 1) {
			res = (res*ant) % 10;
			ant--;
		}
	}*/
	else {
		return 0;
	}
	
	//return res;
}

void resuelveCaso() {
	int num;

	cin >> num;

	int p = fact(num);

	cout << p << "\n";
}

int main() {
	int casos;

	cin >> casos;

	for (int i = 0; i < casos; i++) {
		resuelveCaso();
	}

	return 0;
}