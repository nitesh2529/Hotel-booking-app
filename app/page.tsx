import Link from "next/link"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { CalendarDays, MapPin, Star, Wifi, Car, Coffee, Utensils } from "lucide-react"

export default function HomePage() {
  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
        <div className="container flex h-16 items-center justify-between">
          <div className="flex items-center gap-2">
            <div className="size-8 rounded-lg bg-primary flex items-center justify-center">
              <span className="text-primary-foreground font-bold text-sm">H</span>
            </div>
            <span className="font-semibold text-xl">Hotel Booking</span>
          </div>
          <nav className="hidden md:flex items-center gap-6">
            <Link href="#rooms" className="text-sm font-medium text-muted-foreground hover:text-foreground transition-colors">
              Rooms
            </Link>
            <Link href="#amenities" className="text-sm font-medium text-muted-foreground hover:text-foreground transition-colors">
              Amenities
            </Link>
            <Link href="#about" className="text-sm font-medium text-muted-foreground hover:text-foreground transition-colors">
              About
            </Link>
          </nav>
          <div className="flex items-center gap-3">
            <Button variant="ghost" size="sm">Sign In</Button>
            <Button size="sm">Book Now</Button>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="relative py-24 md:py-32">
        <div className="container">
          <div className="max-w-3xl mx-auto text-center space-y-6">
            <Badge variant="secondary" className="px-4 py-1">
              Luxury Accommodations
            </Badge>
            <h1 className="text-4xl md:text-6xl font-bold tracking-tight text-balance">
              Find Your Perfect Stay
            </h1>
            <p className="text-lg md:text-xl text-muted-foreground text-balance max-w-2xl mx-auto">
              Discover exceptional comfort and world-class hospitality. Book your dream getaway with our curated selection of premium rooms.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center pt-4">
              <Button size="lg" className="gap-2">
                <CalendarDays className="size-4" />
                Check Availability
              </Button>
              <Button size="lg" variant="outline" className="gap-2">
                <MapPin className="size-4" />
                View Locations
              </Button>
            </div>
          </div>
        </div>
      </section>

      {/* Room Types Section */}
      <section id="rooms" className="py-16 md:py-24 bg-muted/50">
        <div className="container">
          <div className="text-center mb-12">
            <h2 className="text-3xl md:text-4xl font-bold mb-4">Our Room Collection</h2>
            <p className="text-muted-foreground max-w-2xl mx-auto">
              From cozy standard rooms to luxurious suites, we have accommodations to suit every preference and budget.
            </p>
          </div>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {[
              { name: "Standard Room", price: "$99", description: "Comfortable essentials for a restful stay", rating: 4.5 },
              { name: "Deluxe Suite", price: "$199", description: "Spacious elegance with premium amenities", rating: 4.8 },
              { name: "Executive Suite", price: "$299", description: "Ultimate luxury for the discerning traveler", rating: 4.9 },
            ].map((room) => (
              <Card key={room.name} className="overflow-hidden hover:shadow-lg transition-shadow">
                <div className="aspect-[4/3] bg-gradient-to-br from-primary/20 to-primary/5 flex items-center justify-center">
                  <div className="text-6xl opacity-50">🛏️</div>
                </div>
                <CardHeader>
                  <div className="flex items-center justify-between">
                    <CardTitle className="text-xl">{room.name}</CardTitle>
                    <div className="flex items-center gap-1 text-sm">
                      <Star className="size-4 fill-yellow-400 text-yellow-400" />
                      <span>{room.rating}</span>
                    </div>
                  </div>
                  <CardDescription>{room.description}</CardDescription>
                </CardHeader>
                <CardContent>
                  <div className="flex items-center justify-between">
                    <div>
                      <span className="text-2xl font-bold">{room.price}</span>
                      <span className="text-muted-foreground text-sm">/night</span>
                    </div>
                    <Button>Book Now</Button>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* Amenities Section */}
      <section id="amenities" className="py-16 md:py-24">
        <div className="container">
          <div className="text-center mb-12">
            <h2 className="text-3xl md:text-4xl font-bold mb-4">Premium Amenities</h2>
            <p className="text-muted-foreground max-w-2xl mx-auto">
              Enjoy a wide range of services and facilities designed to make your stay memorable.
            </p>
          </div>
          <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {[
              { icon: Wifi, name: "Free Wi-Fi", description: "High-speed internet throughout" },
              { icon: Car, name: "Free Parking", description: "Secure on-site parking" },
              { icon: Coffee, name: "Room Service", description: "24/7 in-room dining" },
              { icon: Utensils, name: "Restaurant", description: "Fine dining experience" },
            ].map((amenity) => (
              <Card key={amenity.name} className="text-center">
                <CardContent className="pt-6">
                  <div className="size-12 rounded-full bg-primary/10 flex items-center justify-center mx-auto mb-4">
                    <amenity.icon className="size-6 text-primary" />
                  </div>
                  <h3 className="font-semibold mb-2">{amenity.name}</h3>
                  <p className="text-sm text-muted-foreground">{amenity.description}</p>
                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-16 md:py-24 bg-primary text-primary-foreground">
        <div className="container text-center">
          <h2 className="text-3xl md:text-4xl font-bold mb-4">Ready to Book Your Stay?</h2>
          <p className="text-primary-foreground/80 max-w-2xl mx-auto mb-8">
            Join thousands of satisfied guests who have experienced our exceptional hospitality.
          </p>
          <Button size="lg" variant="secondary">
            Make a Reservation
          </Button>
        </div>
      </section>

      {/* Footer */}
      <footer className="border-t py-12">
        <div className="container">
          <div className="flex flex-col md:flex-row justify-between items-center gap-4">
            <div className="flex items-center gap-2">
              <div className="size-8 rounded-lg bg-primary flex items-center justify-center">
                <span className="text-primary-foreground font-bold text-sm">H</span>
              </div>
              <span className="font-semibold">Hotel Booking</span>
            </div>
            <p className="text-sm text-muted-foreground">
              © {new Date().getFullYear()} Hotel Booking. All rights reserved.
            </p>
          </div>
        </div>
      </footer>
    </div>
  )
}
