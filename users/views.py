from rest_framework.response import Response
from rest_framework import status
from rest_framework.views import APIView

from .serializers import RegisterSerializer

class RegisterView(APIView):
    def post(self, request):
        serializer = RegisterSerializer(data=request.data)
        if serializer.is_valid():
            user = serializer.save()
            if user.is_company:
                return Response({'message': 'Company registration submitted for review.'}, status=status.HTTP_201_CREATED)
            return Response({'message': 'User registered successfully.'}, status=status.HTTP_201_CREATED)
        # Stampa gli errori nel terminale
        print(serializer.errors)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
