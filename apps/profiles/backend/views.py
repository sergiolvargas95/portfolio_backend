from rest_framework import generics
from rest_framework.permissions import IsAuthenticated
from apps.profiles.models import Profile
from .serializers import ProfileSerializer

class ProfileListView(generics.ListCreateAPIView):
    serializer_class = ProfileSerializer
    permission_classes = [IsAuthenticated]

    def get_queryset(self):
        # Obtener todos los perfiles asociados al usuario autenticado
        return Profile.objects.filter(user=self.request.user)

    def perform_create(self, serializer):
        # Asignar el usuario autenticado al perfil
        serializer.save(user=self.request.user)

class ProfileDetailView(generics.RetrieveUpdateDestroyAPIView):
    permission_classes = [IsAuthenticated]
    serializer_class = ProfileSerializer

    def get_queryset(self):
        # Obtener todos los perfiles asociados al usuario autenticado
        return Profile.objects.filter(user=self.request.user)
