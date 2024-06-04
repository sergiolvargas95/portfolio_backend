from django.db import models
from django.contrib.auth.models import User

class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    description = models.TextField(max_length=500, blank=True)
    profile_picture = models.ImageField(upload_to='profile_pictures/', null=True, blank=True)
    job_title = models.CharField(max_length=100)
    curriculum_vitae = models.FileField(upload_to='cirriculums_vitae/', null=True, blank=True)

    def __str__(self):
        return self.user.username
