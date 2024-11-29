from django.contrib.auth.models import AbstractUser
from django.db import models
from django.conf import settings


class CustomUser(AbstractUser):
    is_company = models.BooleanField(default=False)
    phone_number = models.CharField(max_length=15, null=True, blank=True)
    submitted_at = models.DateTimeField(auto_now_add=True)
    email = models.EmailField(unique=True)
    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    username = None

    def __str__(self):
        return self.email


class NormalUser(models.Model):
    user = models.OneToOneField(CustomUser, on_delete=models.CASCADE, related_name='normal_user')
    complete_name = models.CharField(max_length=100, null=True, blank=True)
    fiscal_code = models.CharField(max_length=16, unique=True)
    birth_date = models.DateField()
    address = models.TextField()
    province = models.CharField(max_length=50)
    city = models.CharField(max_length=50)


class Company(models.Model):
    user = models.OneToOneField(CustomUser, on_delete=models.CASCADE, related_name='company')
    company_name = models.CharField(max_length=255)
    vat_number = models.CharField(max_length=20, unique=True)  # Partita IVA unica
    contact_person = models.CharField(max_length=255)
    reviewed_by = models.ForeignKey(
        settings.AUTH_USER_MODEL,
        on_delete=models.SET_NULL,
        null=True,
        related_name='reviewed_requests'
    )
