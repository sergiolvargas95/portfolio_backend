from rest_framework import serializers
from apps.profiles.models import Profile
from django.contrib.auth.models import User

class ProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Profile
        fields = ['description', 'profile_picture', 'job_title', 'curriculum_vitae']