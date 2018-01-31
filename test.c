#include <stdio.h>
#include <string.h>

int main(int argc, char **argv){
	int i, j, len;
	int echoFlag = 0;

	for(i = 1; i < argc; i++){
		len = strlen(argv[i])-1;
		for(j = len; j >= 0; j--){
			if(j >= 3)
				if(argv[i][j] == 'o' || argv[i][j] == 'O')
					if(argv[i][j-1] == 'h' || argv[i][j-1] == 'H')
						if(argv[i][j-2] == 'c' || argv[i][j-2] == 'C')
							if(argv[i][j-3] == 'e' || argv[i][j-3] == 'E')
								echoFlag = 1;

			printf("%c", argv[i][j]);
		}
		printf("%c", (i < argc -1)? ' ': '\0');
	}

	if(echoFlag == 1)
		printf("\necho echo echo");
}
