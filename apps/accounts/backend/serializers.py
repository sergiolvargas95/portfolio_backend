from rest_framework import serializers
from django.contrib.auth.models import User
from django.conf import settings
from django.core.mail import send_mail
from django.urls import reverse
from django.utils.http import urlsafe_base64_encode
from django.utils.encoding import force_bytes
from .tokens import account_activation_token
from django.contrib.sites.models import Site

class UserRegistrationSerializer(serializers.ModelSerializer):
    password = serializers.CharField(write_only=True, required=True, style={'input_type': 'password'})
    password2 = serializers.CharField(write_only=True, required=True, style={'input_type': 'password'})

    class Meta:
        model = User
        fields = ['username', 'password', 'password2', 'email', 'first_name', 'last_name']

    def validate(self, data):
        if data['password'] != data['password2']:
            raise serializers.ValidationError("Passwords do not match")
        return data

    def create(self, validated_data):
        user = User.objects.create(
            username=validated_data['username'],
            email=validated_data['email'],
            first_name=validated_data['first_name'],
            last_name=validated_data['last_name'],
            is_active=False  # Usuario inactivo hasta que confirme el correo
        )
        user.set_password(validated_data['password'])
        user.save()

        # Enviar correo de activación
        token = account_activation_token.make_token(user)
        uid = urlsafe_base64_encode(force_bytes(user.pk))
        activation_link = reverse('activate', kwargs={'uidb64': uid, 'token': token})

        current_site = Site.objects.get_current()
        activation_url = f'http://{current_site.domain}{activation_link}'

        subject = 'Activate Your Account'
        message = f'Hi {user.username},\n\nPlease click the link below to activate your account:\n\n{activation_url}'
        from_email = settings.DEFAULT_FROM_EMAIL
        to_list = [user.email]

        send_mail(subject, message, from_email, to_list)

        return user
