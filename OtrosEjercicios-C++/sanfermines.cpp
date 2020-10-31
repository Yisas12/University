#include <iostream>
using namespace std;

bool resolver()
{
	long long int  toros, mayor = 0, i;

	cin >> toros;
	if (!cin) return false;

	for (int j = 0; j < toros; j++)
	{
		cin >> i;
		
		if (mayor < i) mayor = i;
	}
	
	cout << mayor << endl;

	return true;
}

int main()
{
	//int numeros;
	
	//cin >> numeros;

	while(resolver());

	//system("pause");
	return 0;
}